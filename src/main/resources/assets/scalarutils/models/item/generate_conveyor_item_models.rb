require 'json'

cardinal_directions = %w[north south west east]
speed_levels = %w[slow medium fast]

speed_levels.each do |speed|
  cardinal_directions.each do |direction|
    block_name = "#{speed}_conveyor_#{direction}"
    json_content = {
      parent: "scalarutils:block/#{block_name}"
    }
    File.write("#{block_name}.json", JSON.pretty_generate(json_content))
    puts "Generated #{block_name}.json"
  end
end